#import <React/RCTEventEmitter.h>
#import <React/RCTBridge.h>

@protocol RNEventEmitterDelegate <NSObject>
- (void)didReceiveFormSubmit:(NSDictionary *)formData; // ✅ Semicolon added
@end

@interface RNEventEmitter : RCTEventEmitter <RCTBridgeModule>

// ✅ Declare the shared delegate
+ (void)setSharedDelegate:(id<RNEventEmitterDelegate>)delegate;
+ (id<RNEventEmitterDelegate>)sharedDelegate;

// ✅ Expose sharedInstance to Swift
+ (instancetype)sharedInstance;
- (void)sendEventToReactNative:(NSString *)eventName withData:(NSDictionary *)data;

// ✅ Properly declared sendEventWithName method
//+ (void)sendEventWithName:(NSString *)eventName body:(NSDictionary *)body; // ✅ Semicolon added

// ✅ Ensure React Native can call this function
//RCT_EXPORT_METHOD(sendFormData:(NSDictionary *)formData);  ✅ Semicolon added


// ✅ Store API Data in Native Side
- (void)setApiResponseWheelchair:(NSDictionary *)data;
- (void)setApiResponseInseat:(NSDictionary *)data;

// ✅ Fetch API Data Automatically when View Loads
- (void)fetchApiResponseInseat:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject;
- (void)fetchApiResponseWheelchair:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject;


@end
