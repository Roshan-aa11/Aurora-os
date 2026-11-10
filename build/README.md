# Android source setup

Aurora currently uses the LineageOS 23.2 (Android 16) source base.

## Local manifest

The initial MT6781 source manifest is at `build/local_manifests/aurora.xml`.

After initializing the Android source checkout, copy it into the Repo client's local manifest directory:

```bash
mkdir -p .repo/local_manifests
cp /path/to/Aurora-os/build/local_manifests/aurora.xml .repo/local_manifests/aurora.xml
```

Then Repo can resolve the device, kernel, vendor, and MediaTek SELinux policy projects during sync.

## Product

The initial product definition is in `vendor/aurora/products/`:

- `aurora_fleur.mk`
- `AndroidProducts.mk`

The product reuses the existing shared fleur/miel hardware configuration. It does not replace or fork the device-specific kernel, partition, boot, or vendor configuration.

## Current limitations

This is a source-integration starting point, not a verified build. The complete LineageOS manifest and device-tree dependencies must be resolved before syncing and building. Do not flash anything based on these files yet.
