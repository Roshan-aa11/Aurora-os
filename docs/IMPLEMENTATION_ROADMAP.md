# Aurora OS — Implementation Roadmap

Aurora is being developed as a coordinated Android experience, with a standalone app prototype used to validate the design before system integration.

## Product layers

1. **Aurora Settings app** — complete the visual system, navigation, search, accessibility, and working Android settings intents. Clearly mark prototype-only controls.
2. **Aurora design system** — shared colour tokens, typography, spacing, shapes, icons, motion, light/dark themes, and reusable Compose components.
3. **Aurora Launcher** — home screen, app drawer, widgets, folders, search, gestures, and editorial layout options.
4. **System UI** — status bar, notification shade, Quick Settings, brightness/volume surfaces, media controls, and lock screen. These require Android platform integration; a normal app cannot replace them fully.
5. **Device intelligence** — battery/performance dashboards, audio controls, gaming panel, and contextual features, implemented only where device APIs and hardware support them.
6. **Platform integration** — port the components into the Android 16 / LineageOS 23.2 source tree, resolve device-specific dependencies, build, test, and package.
7. **Release engineering** — signing keys, AVB/OTA configuration, security patch audit, reproducible builds, and recovery/rollback testing.

## Development order

- Finish Settings navigation and page layouts first.
- Extract shared design tokens and reusable UI components.
- Build the Launcher as a separate installable app.
- Prototype System UI surfaces independently, then integrate them into the platform source.
- Bring up each platform component on the POCO test device.
- Produce a flashable ROM only after a successful full platform build and device testing.

## Current constraints and safety

- The current Settings APK is a standalone prototype, not the privileged Android Settings package.
- UI preview switches and sliders do not change hardware or system configuration unless explicitly wired to supported Android APIs.
- Launcher and ordinary apps can be developed on the current laptop. A complete Android platform build cannot realistically be performed on the current 4 GB RAM laptop; a suitable build host will be needed for ROM integration.
- Never flash experimental images to the primary phone. Keep testing on the designated secondary device.
- Do not promise AirDrop interoperability, desktop mode, hardware thermal controls, or other device features until compatibility is verified.

## Definition of done for each component

Each component must have: a documented scope, working navigation and interactions, light/dark theme coverage, accessibility checks, a successful debug build, and device testing. Platform-level components additionally require a successful ROM build and on-device validation.
