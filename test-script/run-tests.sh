#!/bin/bash
set -x
set -u
set -e

# How to Run ?     Just write in console ./run-tests.sh

# Check OS type
if [ "${OSTYPE//[0-9.]/}" == "darwin" ]; then
  export ANDROID_HOME="${HOME}/Library/Android/sdk"
  export PATH=${ANDROID_HOME}/emulator:${ANDROID_HOME}/tools:$PATH

elif [ "${OSTYPE//[0-9.]/}" == "linux-gnu" ]; then
  export ANDROID_HOME="${HOME}/Android/Sdk"
  export PATH=${ANDROID_HOME}/emulator:${ANDROID_HOME}/tools:$PATH

elif [ "${OSTYPE//[0-9.]/}" == "win32" ]; then
  export ANDROID_HOME="${HOME}/Android/Sdk"

else
  printf "I don't know OS Type"
fi

ANDROID_API_LEVEL=28
ADB="${ANDROID_HOME}/platform-tools/adb"
EMULATOR="${ANDROID_HOME}/emulator/emulator"
REPORT="report/"
ANDROID_BUILD_TOOLS_LEVEL=28.0.3

function create_report_folder() {
  if [ -d "$REPORT" ]; then rm -Rf $REPORT; fi
  mkdir -p $REPORT
}

function run_test() {
  cd ..

  create_report_folder

  ./gradlew clean

  # Run projectb android test
  ./gradlew connectedAndroidTest
}

# This function run emulator with default settings.
function wait_emulator_to_be_ready() {
  ${ANDROID_HOME}/tools/bin/sdkmanager --install "platform-tools" "system-images;android-${ANDROID_API_LEVEL};default;x86" "platforms;android-${ANDROID_API_LEVEL}" "build-tools;${ANDROID_BUILD_TOOLS_LEVEL}" "emulator" &&
    yes Y | ${ANDROID_HOME}/tools/bin/sdkmanager --licenses &&
    echo "no" | ${ANDROID_HOME}/tools/bin/avdmanager --verbose create avd --force --name "test" --device "pixel" --package "system-images;android-${ANDROID_API_LEVEL};default;x86" --tag "default" --abi "x86"
  ${ADB} devices | grep ${EMULATOR} | cut -f1 | while read line; do ${ADB} -s $line emu kill; done
  ${EMULATOR} -avd test -memory 4096 -no-audio -no-boot-anim -accel on -skin 1180x2220 &
  boot_completed=false
  while [ "$boot_completed" == false ]; do
    status=$(${ADB} wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    echo "Boot Status: $status"

    if [ "$status" == "1" ]; then
      boot_completed=true
    else
      sleep 1
    fi
  done
}

function generate_report() {
  ${ADB} pull /sdcard/allure-results

  allure generate allure-results --clean -o allure-report

  cp -r ./allure-report/ ./$REPORT

  rm -r allure-report

  rm -r allure-results

}

function deleteEmulator() {
  ${ANDROID_HOME}/tools/bin/avdmanager -v delete avd -n "test"
}

wait_emulator_to_be_ready

run_test

generate_report

${ADB} -e emu kill

deleteEmulator