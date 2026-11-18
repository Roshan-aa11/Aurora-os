# Aurora OS Design System

## Design direction
Editorial minimalism: calm, legible, warm, tactile, and restrained. Aurora should feel like one coherent environment across Settings, Launcher, lock screen and System UI.

## Core palette
| Token | Hex | Role |
|---|---|---|
| Porcelain | #EDECE5 | Light background |
| Charcoal | #252A27 | Primary text |
| Sage | #87968A | Primary accent |
| Mist | #A9B9C0 | Cool secondary accent |
| Clay | #B98775 | Warm secondary accent |
| Sand | #C9BEA8 | Neutral accent |
| Dark Surface | #202522 | Dark background |
| Dark Surface Raised | #2B322E | Dark cards |
| Dark Text | #E9EAE3 | Dark primary text |
| Dark Muted Text | #B8C0B9 | Dark secondary text |

## Shape and spacing
- Controls: 12dp
- Interactive rows: 14dp
- Cards: 20dp
- Large panels: 24dp
- Search field: 18dp
- Spacing scale: 4, 8, 12, 16, 24 and 32dp
- Avoid making every control a pill. Use shape to communicate hierarchy.

## Interaction and motion
- Quick feedback: 120ms
- Standard transitions: 220ms
- Emphasised transitions: 320ms
- Pressed rows use a restrained accent ripple clipped to the row shape.
- Motion should clarify state changes, never delay basic interactions.

## Typography
- Use a clear sans-serif for interface text, with distinct display, body and technical roles.
- Keep headings compact and editorial; secondary descriptions should remain readable.
- Custom font files are not bundled yet. Typeface selection and licensing must be verified before distribution.

## Component rules
- Surfaces use semantic theme roles, not hard-coded colours where practical.
- Interactive rows have a minimum comfortable touch target, clear pressed/focused states and accessible labels.
- Light and dark themes must preserve contrast and semantic colour roles.
- Preview-only controls must be labelled as previews until connected to real system APIs.

## Current implementation
Compose tokens and light/dark Material 3 colour schemes live in `apps/aurora-settings/app/src/main/java/os/aurora/settings/ui/theme/`.
