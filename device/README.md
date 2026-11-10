# Device Support

## Intended test device

A secondary POCO M4 Pro 4G is the initial hardware target. The exact variant and codename are **not yet verified**.

Do not assume that a device name, community codename, or similarly named 5G model identifies the same hardware.

## Required inventory before device bring-up

Collect and verify:

- Exact model number from Settings / device label
- Android version and complete build fingerprint
- Product, device, and manufacturer properties
- SoC and RAM
- Bootloader state
- Active slot / partition layout, where applicable
- Current recovery and a recoverable stock firmware package
- Kernel source and device-tree compatibility for the chosen Android base

## Safe first checks

Use read-only commands first. Do not run erase, format, unlock, or flash commands as part of initial identification.

With USB debugging enabled and ADB installed on the computer:

```sh
adb devices
adb shell getprop ro.product.manufacturer
adb shell getprop ro.product.model
adb shell getprop ro.product.device
adb shell getprop ro.product.name
adb shell getprop ro.build.fingerprint
adb shell getprop ro.soc.model
```

Some properties may be blank or vendor-specific. Save the complete output for review, but redact serial numbers and other identifiers before sharing.

## Bring-up status

- [ ] Confirm exact hardware variant
- [ ] Confirm codename from device properties and independent device documentation
- [ ] Identify a maintained, compatible device tree
- [ ] Identify matching kernel source and vendor firmware
- [ ] Confirm recovery / stock restore path
- [ ] Select Android base only after compatibility review

No device-specific source has been accepted yet.
