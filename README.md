# Aurora OS

An original Android operating system built around editorial minimalism.

## Vision

Aurora OS aims to bring together a calm, distinctive visual identity, meaningful customisation, and practical everyday features on an AOSP foundation.

It is an original project—not a Samsung One UI port or a Pixel UI reskin.

## Design direction

- Editorial minimalism
- Muted, warm colour palette
- Soft but restrained shapes
- Clear information hierarchy
- Consistent typography, iconography, and motion

See [the design system](design/DESIGN_SYSTEM.md) for the current exploratory tokens.

## Project status

**Stage 0 — foundation and feasibility.**

The repository currently contains the project charter, initial design direction, device verification notes, and a lightweight GitHub Actions documentation check.

This workflow does **not** compile Android or build a flashable ROM.

## Initial target device

A secondary POCO M4 Pro 4G is the intended test device. Its exact variant and codename still need to be verified before selecting device sources or attempting any installation.

## Repository map

- `docs/` — project goals and engineering decisions
- `design/` — visual language and design tokens
- `device/` — hardware verification and bring-up notes
- `apps/` — first-party app and interface component roadmap
- `.github/workflows/` — automated repository checks

## Important compatibility notes

AOSP builds, device compatibility, Android certification, Google Mobile Services licensing, and Play Integrity are distinct topics. A successful boot does not guarantee Google certification or every proprietary service.

Aurora OS will document compatibility honestly and will not promise support until it has been tested.

## Safety

Do not flash experimental builds to a daily-use device. Device bring-up must begin with hardware identification, source compatibility checks, and a verified stock recovery path.
