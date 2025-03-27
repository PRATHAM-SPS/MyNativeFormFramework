#import "RNViewManager.h"
#import <React/RCTRootView.h>
#import <React/RCTBridge.h>
#import "RNEventEmitter.h"  // Import the Event Emitter to set the bridge

@implementation RNViewManager

+ (UIView *)getReactNativeView {
    NSURL *jsCodeLocation = [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];

    // ✅ Initialize the React Native bridge
    RCTBridge *bridge = [[RCTBridge alloc] initWithBundleURL:jsCodeLocation moduleProvider:nil launchOptions:nil];

    // ✅ Assign the bridge to RNEventEmitter to allow events to be sent
    [RNEventEmitter sharedInstance].bridge = bridge;
    NSLog(@"✅ Assigned bridge to RNEventEmitter");

    // ✅ Create the root view with the assigned bridge
    RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:bridge moduleName:@"MyFormNative" initialProperties:nil];

    // ✅ Ensure user interaction is enabled
    rootView.userInteractionEnabled = YES;
//    rootView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;

    return rootView;
}


+ (UIView *)getSeatSupportView {
    NSURL *jsCodeLocation = [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];

    // ✅ Initialize the React Native bridge
    RCTBridge *bridge = [[RCTBridge alloc] initWithBundleURL:jsCodeLocation moduleProvider:nil launchOptions:nil];

    // ✅ Assign the bridge to RNEventEmitter to allow events to be sent
    [RNEventEmitter sharedInstance].bridge = bridge;
    NSLog(@"✅ Assigned bridge to RNEventEmitter");

    // ✅ Create the root view with the assigned bridge
    RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:bridge moduleName:@"SeatSupport" initialProperties:nil];

    // ✅ Ensure user interaction is enabled
    rootView.userInteractionEnabled = YES;
//    rootView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;

    return rootView;
}
@end
