"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.MyFormNative = void 0;
exports.multiply = multiply;
var _reactNative = require("react-native");
const LINKING_ERROR = `The package 'form' doesn't seem to be linked. Make sure:\n\n` + _reactNative.Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
}) + '- You rebuilt the app after installing the package\n' + '- You are not using Expo Go\n';

// Check if the native module is available
const MyNativeForm = _reactNative.NativeModules.MyNativeForm ? _reactNative.NativeModules.MyNativeForm : (() => {
  console.warn('MyNativeForm module is not linked properly.');
  return new Proxy({}, {
    get() {
      throw new Error(LINKING_ERROR);
    }
  });
})();

// Function to call the native multiply method
function multiply(a, b) {
  if (!MyNativeForm.multiply) {
    throw new Error("multiply method is not defined in MyNativeForm");
  }
  return MyNativeForm.multiply(a, b);
}

// Ensure native UI component is correctly registered
const MyFormNative = exports.MyFormNative = (0, _reactNative.requireNativeComponent)('MyFormNative');
//# sourceMappingURL=index.js.map