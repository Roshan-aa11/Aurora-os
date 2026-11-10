# Device Support

## Intended test device

The user identifies the physical handset as a **POCO M4 Pro 4G without NFC**.

Current device-reported identifiers:

| Property | Observed value |
|---|---|
| Retail identity | POCO M4 Pro 4G (user-confirmed) |
| Android product device | `miel` |
| Vendor device | `miel` |
| Fastboot product | `miel` |
| Hardware SKU | `mielp` |
| Vendor hardware SKU | `mielp` |
| Hardware revision | `1.19.5` |
| SoC | MediaTek MT6781 |
| Model property | `2201117SG` |
| Product name | `miel_global` |
| Product-partition name | `Fleur` |
| Product mod device | `fleur_tw_global` |
| Android version | 15 |
| Active slot at inspection | `_a` |
| Bootloader | Unlocked (verified with `fastboot getvar unlocked`) |
| Root | Magisk (verified with `adb shell su -c id`) |

## Interpreting the mixed identifiers

The physical handset is user-confirmed as POCO M4 Pro 4G without NFC. The hardware SKU `mielp` is the strongest device-specific discriminator currently observed. The base Fastboot/product properties report `miel`; other build properties include `fleur` and `missi`.

These values are not necessarily contradictory: custom ROMs and shared device-family trees may use common product/vendor identities while retaining a variant SKU. Do not infer the target source configuration from one property alone.

The model property `2201117SG` is commonly catalogued under a closely related Redmi model, so it should not override the physical device identity and SKU without further evidence.

## Source-tree lead

The public `xiaomi-mt6781-fleur-dev/android_device_xiaomi_fleur` tree identifies the shared Redmi Note 11S / POCO M4 Pro 4G family. Its `BoardConfig.mk` sets:

```make
TARGET_OTA_ASSERT_DEVICE := fleur,fleur_p,miel,miel_p
```

This explicitly includes the `miel_p` variant in its OTA device assertions. This is a promising shared-family source candidate, not proof that every image produced from that tree is safe or compatible with the current firmware. Audit the product makefiles, SKU-specific init/configuration, kernel/DTB, vendor blobs, and firmware baseline before adoption.

References:
- https://github.com/xiaomi-mt6781-fleur-dev/android_device_xiaomi_fleur
- https://github.com/xiaomi-mt6781-fleur-dev/android_device_xiaomi_fleur/blob/lineage-20.0/BoardConfig.mk
- https://sourceforge.net/projects/fleur-files/

## Required inventory before device bring-up

Collect and verify:

- Exact retail model and regulatory model number from the handset label / box
- Product, device, vendor, and hardware SKU properties
- SoC and RAM
- Bootloader state
- Active slot / partition layout, where applicable
- Current firmware/vendor build and a recoverable stock firmware package
- Kernel source and device-tree compatibility for the chosen Android base

## Safe read-only checks

With USB debugging enabled and ADB installed:

```sh
adb devices
adb shell getprop ro.product.manufacturer
adb shell getprop ro.product.model
adb shell getprop ro.product.device
adb shell getprop ro.product.name
adb shell getprop ro.product.vendor.device
adb shell getprop ro.boot.product.hardware.sku
adb shell getprop ro.build.fingerprint
adb shell getprop ro.soc.model
```

Bootloader queries (read-only):

```sh
fastboot getvar unlocked
fastboot getvar product
fastboot getvar is-userspace
```

Do not run erase, format, unlock, lock, or flash commands during identification.

## Bring-up status

- [x] Collect initial device properties
- [x] Verify root access
- [x] Verify unlocked bootloader via Fastboot
- [x] Record A/B slot and dynamic partition indicators
- [x] Identify POCO non-NFC variant using user confirmation and `mielp` SKU
- [x] Find a shared-family device tree whose OTA asserts include `miel_p`
- [ ] Audit product makefiles and variant-specific configuration
- [ ] Identify matching kernel source and vendor firmware
- [ ] Confirm recovery / stock restore path
- [ ] Select Android base only after compatibility review

No device-specific source has been accepted for a build yet.


## Source audit — LineageOS 23.2 candidate (2026-11-10)

Inspected the public `mt6781-devs/android_device_xiaomi_fleur` repository at ref `lineage-23.2`.

Observed:

- `BoardConfig.mk` targets `mt6781`, arm64, A/B / Virtual A/B, and dynamic partitions.
- It uses `TARGET_KERNEL_SOURCE := kernel/xiaomi/mt6781` and `TARGET_KERNEL_CONFIG := fleur_defconfig`.
- It defines dynamic partitions for system, system_ext, product, vendor, and odm.
- `device.mk` copies files from a `sku/` directory into ODM and imports a build property file selected by `ro.boot.product.vendor.sku` through `odm.prop`. This makes the reported hardware SKU relevant to variant-specific configuration.
- `lineage_fleur.mk` still sets the generic product identity to `fleur` and Redmi branding. This is a shared-family product configuration, not proof that a generated image is already tailored correctly for the POCO SKU.
- The tree includes shared NFC-related packages and SKU-specific permission XMLs. The exact `mielp` / non-NFC behavior must be checked in the SKU files and runtime feature declarations; unconditional package inclusion alone does not prove NFC is exposed to the OS.
- The README identifies the tree as the Redmi Note 11S 4G / POCO M4 Pro 4G family.

Important correction: the older `xiaomi-mt6781-fleur-dev/android_device_xiaomi_fleur` branch `lineage-20.0` is Android 13-era and must not be confused with the newer `mt6781-devs/android_device_xiaomi_fleur` branch `lineage-23.2`.

Current assessment: `lineage-23.2` is a promising Android 16-era shared-family bring-up candidate, but not yet approved for Aurora. The next audit must verify the exact `mielp` SKU configuration, kernel repository/branch, vendor repository/branch, firmware compatibility, and build manifest/dependencies.

Inspected source:
- https://github.com/mt6781-devs/android_device_xiaomi_fleur/tree/lineage-23.2
- https://github.com/mt6781-devs/android_device_xiaomi_fleur/blob/lineage-23.2/BoardConfig.mk
- https://github.com/mt6781-devs/android_device_xiaomi_fleur/blob/lineage-23.2/device.mk
- https://github.com/mt6781-devs/android_device_xiaomi_fleur/blob/lineage-23.2/lineage_fleur.mk
