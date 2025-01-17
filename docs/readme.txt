# SpaceShipFinder

SpaceShipFinder is a game developed for **CMPT 276 (Introduction to Software Engineering)** over a span of 2 weeks. The objective of the game is to find all the hidden spaceships with the least number of tries, using the number clues provided for each row and column.

---

## Features

### Gameplay
- **Find Hidden Spaceships**: Use number clues to identify where spaceships are hidden on the board.
- **Dynamic Challenges**: Board dimensions and the number of hidden spaceships vary based on user settings.

### Customization
- **Adjustable Board Size**: Choose from 4x6, 5x10, or 6x15 board dimensions.
- **Selectable Difficulty**: Set the number of spaceships to 6, 10, 15, or 20.
- **Persistent Settings**: User preferences are saved using Android's `SharedPreferences`, so your settings are retained for the next session.

### User Feedback
- **Toast Messages**: Immediate feedback is provided to users on their selections.
- **Victory Screen**: Celebrate your win with a dedicated screen when you successfully find all spaceships.

---

## Technologies Used
- **Android Studio**: Development environment for building the app.
- **Java**: Programming language used for the implementation.
- **SharedPreferences**: Used to store user settings such as board dimensions and the number of spaceships.

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repository/spaceshipfinder.git
   ```
2. Open the project in **Android Studio**.
3. Build and run the project on an emulator or a physical Android device.

---

## How to Play
1. Open the app and navigate to the **Game Settings** screen.
2. Choose your preferred **board dimensions** and **number of spaceships**.
3. Confirm your settings and start the game.
4. Use the clues provided for each row and column to locate all hidden spaceships.
5. Win the game by finding all spaceships with the fewest number of tries possible.

---

## Screenshots

### Gameplay
![Gameplay Screenshot](path/to/gameplay_image.png)

### Victory Screen
![Victory Screenshot](path/to/victory_image.png)

---

## Challenges Faced
- **Mathematics and Mechanics**: Designing the logic for how spaceships would move and interact was one of the most challenging aspects of development.

---

## Future Enhancements
- Add more difficulty levels.
- Implement animations for spaceship movement.
- Integrate a leaderboard to track high scores.

---

## Contributors
- **Kiarash** and team

---

## License
This project is licensed under the [MIT License](LICENSE).

