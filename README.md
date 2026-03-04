# ObjectFight 🧱🚀

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-Swing-blue.svg)](https://www.java.com/)

[🇪🇸 Español](#-español) | [🇬🇧 English](#-english)

---

## 🇪🇸 Español

### 📖 Índice
1. [Acerca del Proyecto](#acerca-del-proyecto)
2. [Características principales](#características-principales)
3. [Requisitos](#requisitos)
4. [Instalación y Ejecución](#instalación-y-ejecución)
5. [Licencia](#licencia)

### Acerca del Proyecto
**ObjectFight** es un juego arcade desarrollado en **Java Swing**. En este divertido título, el jugador controla una plataforma (Player) para hacer rebotar una pelota (Ball) y destruir los muros (Walls) en pantalla a través de diversas fases. ¡Pon a prueba tus reflejos, gestiona tus vidas y supera todos los niveles para alcanzar la victoria!

### Características principales
*   **Físicas de colisión**: Implementación de colisiones rectangulares entre los distintos objetos del juego (Pelota, Jugador, Muros).
*   **Sistema de fases**: Supera las 5 fases de dificultad creciente destruyendo todos los muros de la zona de juego.
*   **Puntuación y Vidas**: Gana puntos por cada muro destruido. ¡Pero cuidado! Si la pelota toca el suelo, perderás una de tus 3 vidas.
*   **Controles fluidos**: Movimiento suave de la plataforma usando las flechas direccionales o las teclas A/D.
*   **Interfaz Gráfica (GUI)**: Pantalla de menú principal, renderizado en tiempo real y Game Over / Victory screens usando la API gráfica estándar de Java.

### Requisitos
*   **Java Development Kit (JDK)**: Versión 8 o superior.
*   Entorno de desarrollo (IDE) como Eclipse, IntelliJ IDEA, VS Code, o simplemente terminal.

### Instalación y Ejecución
1.  **Clona este repositorio**:
    ```bash
    git clone https://github.com/TU_USUARIO/ObjectFight.git
    cd ObjectFight
    ```
2.  **Compilación y Ejecución (vía terminal)**:
    ```bash
    javac -d bin src/main/java/com/xanxa/objectfight/**/*.java
    java -cp bin com.xanxa.objectfight.Main
    ```
    *Alternativamente, puedes importar el proyecto en tu IDE favorito y ejecutar la clase `Main.java`.*

### Licencia
Este proyecto está bajo la Licencia **MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 🇬🇧 English

### 📖 Table of Contents
1. [About the Project](#about-the-project)
2. [Key Features](#key-features)
3. [Requirements](#requirements)
4. [Installation and Execution](#installation-and-execution)
5. [License](#license-1)

### About the Project
**ObjectFight** is an arcade game developed using **Java Swing**. In this fun title, the player controls a paddle (Player) to bounce a ball (Ball) and destroy all the walls (Walls) on the screen across various phases. Test your reflexes, manage your lives, and beat all levels to achieve victory!

### Key Features
*   **Collision Physics**: Implementation of rectangular collisions between the different game objects (Ball, Player, Walls).
*   **Phase System**: Beat the 5 phases of increasing difficulty by destroying all the walls in the game zone.
*   **Score & Lives**: Earn points for every destroyed wall. But be careful! If the ball hits the floor, you'll lose one of your 3 lives.
*   **Fluid Controls**: Smooth paddle movement using directional arrows or A/D keys.
*   **Graphical Interface (GUI)**: Main menu screen, real-time rendering, and Game Over / Victory screens using standard Java Graphics API.

### Requirements
*   **Java Development Kit (JDK)**: Version 8 or higher.
*   Development environment (IDE) like Eclipse, IntelliJ IDEA, VS Code, or just a terminal.

### Installation and Execution
1.  **Clone this repository**:
    ```bash
    git clone https://github.com/YOUR_USERNAME/ObjectFight.git
    cd ObjectFight
    ```
2.  **Compilation and Execution (via terminal)**:
    ```bash
    javac -d bin src/main/java/com/xanxa/objectfight/**/*.java
    java -cp bin com.xanxa.objectfight.Main
    ```
    *Alternatively, you can import the project into your favorite IDE and run the `Main.java` class.*

### License
This project is licensed under the **MIT** License. See the [LICENSE](LICENSE) file for more information.
