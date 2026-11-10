# Aurora OS — Project Charter

## Vision

Aurora OS is an original Android operating system built around editorial minimalism: calm visual hierarchy, purposeful motion, thoughtful customisation, and practical everyday features.

It may take inspiration from useful ideas across Android interfaces, but it will have its own visual language, implementation, and identity. It is not a Samsung One UI port or a Pixel UI reskin.

## Product principles

1. **Clarity before decoration** — information should be easy to scan.
2. **Customisation with restraint** — meaningful controls without overwhelming settings.
3. **Consistency** — typography, spacing, surfaces, icons, and motion should work as one system.
4. **Performance** — visual features must respect the limits of the target hardware.
5. **Privacy and security** — no hidden tracking, unsafe defaults, or unexplained privileged services.
6. **Honest compatibility** — clearly document what works, what is experimental, and what cannot be guaranteed.

## Initial design direction

- Personality: editorial minimalism
- Palette: muted, warm neutrals with restrained accent colours
- Shapes: soft, moderate corner radii
- UI typography: IBM Plex Sans (proposed)
- Editorial display typography: Newsreader (proposed)
- Technical/numeric typography: IBM Plex Mono (proposed)

Font choices are provisional until package availability and licences are checked.

## Initial feature areas

- System UI: status bar, notifications, Quick Settings/control centre, navigation and animations
- Lock screen and launcher
- Theme and personalisation engine
- Settings experience
- Battery, performance and gaming tools, subject to hardware support
- Audio controls and profiles
- File sharing experience, including a practical cross-platform fallback
- Optional desktop-style interface, subject to display-output and input-device capabilities

## Engineering approach

Aurora OS will be based on AOSP. Device-specific work will require a verified device identity, compatible device tree, kernel, vendor components, and a supported Android base.

Google Mobile Services, Android compatibility certification, Play Integrity, and cross-device sharing interoperability are separate matters. None should be represented as guaranteed merely because an AOSP build boots.

## First target

The intended test device is a secondary POCO M4 Pro 4G. Its exact product identity, codename, firmware, partition layout, and available source compatibility must be verified before selecting a device tree or attempting to flash anything.

The primary phone must remain untouched.

## Current stage

**Stage 0 — project foundation.**

No device tree has been adopted, no Android release has been selected, and no ROM has been built or flashed. The first CI workflow validates repository documentation only; it is not an Android build workflow.
