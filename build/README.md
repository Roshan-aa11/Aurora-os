# Android source setup

Aurora currently uses the LineageOS 23.2 (Android 16) source base.

## Local manifest

The initial MT6781 source manifest is at `build/local_manifests/aurora.xml`.

After initializing the Android source checkout, copy it into the Repo client's local manifest directory:

```bash
mkdir -p .repo/local_manifests
cp /path/to/Aurora-os/build/local_manifests/aurora.xml .repo/local_manifests/aurora.xml
```

Then Repo can resolve the device, kernel, vendor, MediaTek hardware, Xiaomi hardware, and MediaTek SELinux policy projects during sync. Google Pixel hardware components are supplied by the base LineageOS manifest.

## Product

The initial product definition is in `vendor/aurora/products/`:

- `aurora_fleur.mk`
- `AndroidProducts.mk`

The product inherits the standard 64-bit-only and telephony product foundations, then reuses the shared fleur/miel hardware configuration. It does not replace or fork the device-specific kernel, partition, boot, or vendor configuration.

## Bring-up audit items

These are known items to verify before treating an image as release-ready:

- The upstream device tree currently declares a vendor security patch level of `2024-12-01`. This must be checked against the actual vendor firmware and updated appropriately; do not simply claim a newer vendor patch level.
- The device tree uses Android test AVB signing keys. Aurora needs its own release signing keys and a verified signing/rollback configuration before distribution.
- The device tree includes LineageOS overlays and components. They are retained for initial hardware bring-up and can be replaced as Aurora's interface is developed.
- The vibrator service has a Qualcomm-style package name but its implementation accesses the device's AW8622 haptic sysfs interface. It is retained unchanged pending on-device testing.

## Current limitations

This is a source-integration starting point, not a verified build. The complete LineageOS manifest and device-tree dependencies must be resolved before syncing and building. The current development laptop has limited memory and CPU capacity, so a full Android build should not be attempted locally without first arranging a suitable build machine. Do not flash anything based on these files yet.
