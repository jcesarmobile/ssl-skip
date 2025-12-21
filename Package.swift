// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "JcesarmobileSslSkip",
    platforms: [.iOS(.v15)],
    products: [
        .library(
            name: "JcesarmobileSslSkip",
            targets: ["SslSkipPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "8.0.0")
    ],
    targets: [
        .target(
            name: "SslSkipPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")],
            path: "ios/Sources/SslSkipPlugin",
            publicHeadersPath: "include")
    ]
)