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
| Hardware revision | `1.19.5` |
| SoC | MediaTek MT6781 |
| Model property | `2201117SG` |
| Product name | `miel_global` |
| Android version | 15 |
| Active slot at inspection | `_a` |
| Bootloader | Unlocked (verified with `fastboot getvar unlocked`) |
| Root | Magisk (verified with `adb shell su -c id`) |

### Identity caveat

The physical handset is user-confirmed as POCO M4 Pro 4G without NFC. The reported `mielp` hardware SKU is a useful variant discriminator, while Android/Fastboot report the base product as `miel`. Do not infer the correct source-tree path from one property alone.

The model property `2201117SG` is commonly catalogued under a closely related Redmi model. This discrepancy is not resolved by the software properties alone. Verify against the handset's regulatory label and authoritative device documentation before selecting firmware or device-specific sources.

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
- [ ] Resolve retail model / hardware SKU against official device documentation
- [ ] Identify a maintained, compatible device tree
- [ ] Identify matching kernel source and vendor firmware
- [ ] Confirm recovery / stock restore path
- [ ] Select Android base only after compatibility review

No device-specific source has been accepted yet.
