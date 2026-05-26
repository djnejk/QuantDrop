# QuantDrop

QuantDrop is a client-side Fabric mod for Minecraft `26.1.2` that lets you quickly throw items out of open inventory screens.

## Features

- Drop every item from the currently opened container with a single shortcut.
- Drop every matching item from the section currently under the cursor.
- Both actions are configurable in Minecraft under `Controls -> Key Binds -> QuantDrop`.

## Default Shortcuts

- `Ctrl + Alt + =`: Drop all items from the currently opened container.
- `Ctrl + Alt + [`: Drop all matching items from the hovered inventory section.

## Supported Screens

- Chest
- Double chest
- Hopper
- Dispenser
- Dropper
- Shulker box
- Player inventory sections inside handled container screens

## Installation

1. Install Minecraft `26.1.2`.
2. Install the Fabric Loader for `26.1.2`.
3. Install Fabric API.
4. Copy the mod jar from `build/libs/quantdrop-1.0.0.jar` into your `mods` folder.

## Development

Build the project with:

```powershell
.\gradlew.bat build
```

The built jar will be generated in `build/libs/`.

## License

This project is licensed under the MIT License. You may use, modify, fork, and redistribute it, including commercially, as long as the original copyright and author notice are preserved.
