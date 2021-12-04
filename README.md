# @jcesarmobile/ssl-skip

Capacitor plugin for skipping ssl checks.

When trying to use a local web server with self signed certificates to serve a Capacitor app from a https url, it fails to load because of self signed certificates not being considered secure by the OS.

This plugin skips the SSL checks so the app loads.

## Install

```bash
npm install @jcesarmobile/ssl-skip
npx cap sync
```

## Warning

This plugin is only meant to be used during development, publishing an app with this plugin installed can lead to app rejections on Google Play Store and App Store.
Before submitting your app remember to uninstall the plugin and run sync command again.

```bash
npm uninstall @jcesarmobile/ssl-skip
npx cap sync
```
