# Aurora OS Design System

This document records the current visual direction. Values are exploratory design tokens, not implemented system resources.

## Identity

Editorial minimalism: composed layouts, clear hierarchy, warm surfaces, restrained colour, and purposeful motion.

## Exploratory colour palette

| Token | Hex | Intended role |
|---|---|---|
| Porcelain | `#EDECE5` | Light theme background |
| Charcoal | `#252A27` | Dark theme background / primary text |
| Sage | `#87968A` | Primary accent |
| Mist | `#A9B9C0` | Secondary accent |
| Clay | `#B98775` | Warm accent |
| Sand | `#C9BEA8` | Neutral supporting surface |

These colours need contrast testing before use for text, controls, or accessibility-critical states.

## Typography proposals

- IBM Plex Sans — interface text
- Newsreader — selective editorial display, such as lock-screen clock treatments
- IBM Plex Mono — technical values and compact data

Confirm font licences and Android integration requirements before bundling fonts.

## Shape and layout

- Soft, moderate corner radii; avoid turning every control into a pill.
- Strong spacing rhythm and aligned edges.
- Dense and spacious layout options where they serve different usage styles.
- Avoid copying Pixel, Nothing OS, or Samsung component layouts directly.

## Iconography

- System icons: consistent, restrained monochrome outline family.
- App icons: explore soft two-tone muted treatments.
- Status indicators must remain legible at the target display density and accommodate notification and privacy indicators.

## Components to design

1. Status bar and system icon family
2. Quick Settings / control centre
3. Lock screen
4. Home screen and launcher
5. Notification surfaces
6. Settings navigation and controls
7. Theme and wallpaper controls

## Accessibility checks

Test light and dark themes, contrast, font scaling, display scaling, touch target sizes, colour-independent state cues, and reduced-motion behaviour.
