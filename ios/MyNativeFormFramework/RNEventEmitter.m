#import "RNEventEmitter.h"


@interface RNEventEmitter ()
@property (nonatomic, strong) NSDictionary *storedApiDataWheelchair;
@property (nonatomic, strong) NSDictionary *storedApiDataInseat;
@end

@implementation RNEventEmitter

RCT_EXPORT_MODULE(RNEventEmitter);

// ✅ Singleton instance
static RNEventEmitter *sharedInstance = nil;
static id<RNEventEmitterDelegate> sharedDelegate = nil; // ✅ Shared delegate reference

+ (instancetype)sharedInstance {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedInstance = [[RNEventEmitter alloc] init];
        NSLog(@"✅ RNEventEmitter sharedInstance created.");
    });
    return sharedInstance;
}

// ✅ Assign delegate globally
+ (void)setSharedDelegate:(id<RNEventEmitterDelegate>)delegate {
    sharedDelegate = delegate;
    NSLog(@"✅ RNEventEmitter sharedDelegate set.");
}

// ✅ Get shared delegate
+ (id<RNEventEmitterDelegate>)sharedDelegate {
    return sharedDelegate;
}


// ✅ Ensure React Native registers events
- (NSArray<NSString *> *)supportedEvents {
    return @[@"onNativeData"];
}

// ✅ Called when React Native initializes the bridge
- (void)startObserving {
    NSLog(@"✅ RNEventEmitter has started observing events.");
}

// ✅ Called when React Native destroys the bridge
- (void)stopObserving {
    NSLog(@"⚠️ RNEventEmitter stopped observing events.");
}

// ✅ Method to Store API Data (Called from Swift)
- (void)setApiResponseWheelchair:(NSDictionary *)data {
    sharedInstance.storedApiDataWheelchair = data;
    NSLog(@"📡 iOS: API response stored in RNEventEmitter -> %@", sharedInstance.storedApiDataWheelchair);
}

- (void)setApiResponseInseat:(NSDictionary *)data {
    sharedInstance.storedApiDataInseat = data;
    NSLog(@"📡 iOS: API response stored in RNEventEmitter -> %@", sharedInstance.storedApiDataInseat);
}



// ✅ Fetch Data when React Native Loads
RCT_EXPORT_METHOD(fetchApiResponseWheelchair:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    if (sharedInstance.storedApiDataWheelchair) {
        resolve(sharedInstance.storedApiDataWheelchair);
        NSLog(@"🚀 iOS: Sending stored API data to React Native -> %@", sharedInstance.storedApiDataWheelchair);
    } else {
        reject(@"E_NO_DATA", @"No API data available", nil);
    }
}

RCT_EXPORT_METHOD(fetchApiResponseInseat:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    if (sharedInstance.storedApiDataInseat) {
        resolve(sharedInstance.storedApiDataInseat);
        NSLog(@"🚀 iOS: Sending stored API data to React Native -> %@", sharedInstance.storedApiDataInseat);
    } else {
        reject(@"E_NO_DATA", @"No API data available", nil);
    }
}

// ✅ Called from React Native directly
RCT_EXPORT_METHOD(sendFormData:(NSDictionary *)formData) {
    NSLog(@"📩 Objective-C: Received form data from React Native: %@", formData);

    // ✅ Trigger Swift delegate if available
    id<RNEventEmitterDelegate> delegate = [RNEventEmitter sharedDelegate];
    if (delegate) {
        NSLog(@"📡 Objective-C: Triggering shared delegate method in Swift...");
        [delegate didReceiveFormSubmit:formData];
    } else {
        NSLog(@"⚠️ Objective-C: Shared delegate not set. Swift won't receive event.");
    }
}

- (void)sendEventToReactNative:(NSString *)eventName withData:(NSDictionary *)data {
    if (self.bridge) {
        NSLog(@"📡 Sending event '%@' to React Native with data: %@", eventName, data);
        [self sendEventWithName:eventName body:data];
    } else {
        NSLog(@"⚠️ Bridge is niddddl. Cannot send event.");
    }
}

@end
