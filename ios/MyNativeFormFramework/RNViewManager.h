//RNViewManager.h
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@interface RNViewManager: NSObject
+ (UIView *)getReactNativeView;
+(UIView *)getSeatSupportView;
+(UIView *)getStaticBox;
@end
