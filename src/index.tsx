import { NativeModules, requireNativeComponent, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'form' doesn't seem to be linked. Make sure:\n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// Check if the native module is available
const MyNativeForm = NativeModules.MyNativeForm
  ? NativeModules.MyNativeForm
  : (() => {
      console.warn('MyNativeForm module is not linked properly.');
      return new Proxy(
        {},
        {
          get() {
            throw new Error(LINKING_ERROR);
          },
        }
      );
    })();

// Function to call the native multiply method
export function multiply(a: number, b: number): Promise<number> {
  if (!MyNativeForm.multiply) {
    throw new Error("multiply method is not defined in MyNativeForm");
  }
  return MyNativeForm.multiply(a, b);
}

// Ensure native UI component is correctly registered
export const MyFormNative = requireNativeComponent('MyFormNative');
