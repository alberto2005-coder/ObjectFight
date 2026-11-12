# ObjectFight

[![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.java.com/)
[![Swing](https://img.shields.io/badge/UI-Java%20Swing-5382A1)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![MIT](https://img.shields.io/badge/Licencia-MIT-green)](LICENSE)

[Español](#español) · [English](#english)

---

## Español

### Índice

- [Descripción](#descripción)
- [Características](#características)
- [Controles](#controles)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Compilación y ejecución](#compilación-y-ejecución)
- [Licencia](#licencia)

### Descripción

**ObjectFight** es un arcade estilo *breakout* escrito en **Java Swing**. Controlas una plataforma para hacer rebotar una pelota y destruir todos los muros de la pantalla, fase tras fase, sin quedarte sin vidas.

### Características

- **Físicas de colisión** rectangulares entre pelota, plataforma y muros.
- **Cinco fases** de dificultad creciente: hay que limpiar todos los muros para pasar.
- **Puntuación y vidas**: cada muro suma puntos y cada caída de la pelota cuesta una de las tres vidas.
- **Movimiento fluido** con flechas o A/D.
- **Interfaz completa**: menú principal, partida en tiempo real y pantallas de derrota y victoria.

### Controles

| Tecla | Acción |
|---|---|
| `←` / `A` | Mover la plataforma a la izquierda |
| `→` / `D` | Mover la plataforma a la derecha |
| `Esc` | Volver al menú (según pantalla) |

### Estructura del proyecto

```text
ObjectFight/
├── src/main/java/com/xanxa/objectfight/
│   ├── Main.java               # Punto de entrada
│   ├── logic/
│   │   ├── GameManager.java    # Estados, puntuación y fases
│   │   ├── Collider.java       # Interfaz de colisión
│   │   └── RectangleCollider.java
│   ├── model/
│   │   ├── GameObject.java     # Base de los objetos del juego
│   │   ├── Ball.java · Player.java · Wall.java
│   └── view/
│       ├── GameWindow.java     # Ventana principal
│       ├── GameCanvas.java     # Render en tiempo real
│       └── MainMenu.java
├── Manifest.txt
├── LICENSE
└── README.md
```

### Requisitos

- **JDK 8 o superior**.
- Un IDE (IntelliJ IDEA, Eclipse, VS Code) o simplemente un terminal.

### Compilación y ejecución

```bash
git clone https://github.com/alberto2005-coder/ObjectFight.git
cd ObjectFight
javac -d bin src/main/java/com/xanxa/objectfight/**/*.java
java -cp bin com.xanxa.objectfight.Main
```

En PowerShell, si el comodín `**` no se expande, compila indicando los paquetes o importa el proyecto en tu IDE y ejecuta `Main.java`.

### Licencia

**MIT** — consulta [LICENSE](LICENSE).

---

## English

### Table of contents

- [Overview](#overview)
- [Features](#features)
- [Controls](#controls)
- [Project structure](#project-structure)
- [Requirements](#requirements)
- [Build and run](#build-and-run)
- [License](#license)

### Overview

**ObjectFight** is a *breakout*-style arcade game written in **Java Swing**. You control a paddle to bounce a ball and destroy every wall on screen, level after level, without running out of lives.

### Features

- Rectangular **collision physics** between ball, paddle and walls.
- **Five phases** of increasing difficulty: clear every wall to advance.
- **Score and lives**: each wall scores points and each ball drop costs one of your three lives.
- **Smooth movement** with the arrow keys or A/D.
- **Complete interface**: main menu, real-time gameplay and game-over/victory screens.

### Controls

| Key | Action |
|---|---|
| `←` / `A` | Move the paddle left |
| `→` / `D` | Move the paddle right |
| `Esc` | Back to the menu (depending on the screen) |

### Project structure

```text
ObjectFight/
├── src/main/java/com/xanxa/objectfight/
│   ├── Main.java               # Entry point
│   ├── logic/
│   │   ├── GameManager.java    # States, score and phases
│   │   ├── Collider.java       # Collision interface
│   │   └── RectangleCollider.java
│   ├── model/
│   │   ├── GameObject.java     # Base game object
│   │   ├── Ball.java · Player.java · Wall.java
│   └── view/
│       ├── GameWindow.java     # Main window
│       ├── GameCanvas.java     # Real-time rendering
│       └── MainMenu.java
├── Manifest.txt
├── LICENSE
└── README.md
```

### Requirements

- **JDK 8 or later**.
- An IDE (IntelliJ IDEA, Eclipse, VS Code) or just a terminal.

### Build and run

```bash
git clone https://github.com/alberto2005-coder/ObjectFight.git
cd ObjectFight
javac -d bin src/main/java/com/xanxa/objectfight/**/*.java
java -cp bin com.xanxa.objectfight.Main
```

On PowerShell, if `**` is not expanded, compile per package or import the project into your IDE and run `Main.java`.

### License

**MIT** — see [LICENSE](LICENSE).
