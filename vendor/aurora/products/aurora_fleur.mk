# Aurora OS product definition for the shared MT6781 fleur/miel device family.
# Hardware configuration remains in the upstream device tree.

# Match the upstream device's required Android product foundations.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

$(call inherit-product, device/xiaomi/fleur/device.mk)
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_NAME := aurora_fleur
PRODUCT_DEVICE := fleur
PRODUCT_BRAND := POCO
PRODUCT_MODEL := POCO M4 Pro
PRODUCT_MANUFACTURER := Xiaomi
