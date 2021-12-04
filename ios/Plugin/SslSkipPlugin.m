#import <WebKit/WebKit.h>
#import <objc/message.h>
#import <objc/runtime.h>

@implementation NSObject (WKNavigationDelegate)
 
+ (void)load {
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
    Class class = [self class];
    SEL originalSelector = @selector(webView:didReceiveAuthenticationChallenge:completionHandler:);
    SEL swizzledSelector = @selector(SkipSSLwebView:didReceiveAuthenticationChallenge:completionHandler:);

    Method originalMethod = class_getInstanceMethod(class, originalSelector);
    Method swizzledMethod = class_getInstanceMethod(class, swizzledSelector);

    BOOL didAddMethod = class_addMethod(class,
                                        originalSelector,
                                        method_getImplementation(swizzledMethod),
                                        method_getTypeEncoding(swizzledMethod));
    if (didAddMethod) {
      class_replaceMethod(class,
                          swizzledSelector,
                          method_getImplementation(originalMethod),
                          method_getTypeEncoding(originalMethod));
    } else {
      method_exchangeImplementations(originalMethod, swizzledMethod);
    }
  });
}

- (void)SkipSSLwebView:(WKWebView *)webView didReceiveAuthenticationChallenge:(NSURLAuthenticationChallenge *)challenge completionHandler:(void (^)(NSURLSessionAuthChallengeDisposition disposition, NSURLCredential *credential))completionHandler{
  NSURLCredential * credential = [[NSURLCredential alloc] initWithTrust:[challenge protectionSpace].serverTrust];
  completionHandler(NSURLSessionAuthChallengeUseCredential, credential);
}

@end
