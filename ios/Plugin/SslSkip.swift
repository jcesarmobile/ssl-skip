import Foundation

@objc public class SslSkip: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
