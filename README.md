# P-Man 👻

A terminal-based Pac-Man clone written in Java, featuring 3 levels with progressively larger mazes, ghost AI, and a food-collection scoring system.

## Gameplay

- Navigate Pac-Man (`P`) through the maze eating food dots (`·`)
- Avoid 4 ghosts (`G`) that actively chase you
- Clear all food dots to advance to the next level
- You start with 3 lives — lose one every time a ghost catches you
- Complete all 3 levels to win

## Controls

| Key | Direction |
|-----|-----------|
| `W` | Up        |
| `S` | Down      |
| `A` | Left      |
| `D` | Right     |

## Project Structure

```
├── Main.java             # Game loop and level management
├── PacMan.java           # Pac-Man movement and collision logic
├── Ghost.java            # Ghost AI (chases Pac-Man by shortest distance)
├── BReaderByIndex.java   # Reads maze layouts from file, places entities
├── DirectionInput.java   # Handles keyboard input
└── FBs.txt               # Maze data for all 3 levels
```

## How to Run

**Requirements:** Java 8 or higher

```bash
# Compile
javac *.java

# Run (from the directory containing FBs.txt)
java Main
```

> Make sure `FBs.txt` is in the same directory as the compiled classes.

## Features

- **3 unique levels** — each with a different maze layout stored in `FBs.txt`
- **Ghost AI** — ghosts calculate the shortest Euclidean distance to Pac-Man each turn and move accordingly
- **Wrap-around movement** — Pac-Man wraps around the edges of the board
- **Food tracking** — score increments per dot eaten; level ends when all dots are cleared
- **Respawn** — on losing a life, Pac-Man respawns at a random open position

## Notes

- **Level parsing is offset-based** — `BReaderByIndex.java` uses a hardcoded line number (`l_no = 128`) to know where to start reading `FBs.txt`. This works as long as the file is not edited. If you add/remove walls or reorder levels, update that value accordingly or the wrong maze data will load.