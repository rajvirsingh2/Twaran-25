# Twaran-25 🏆

**Twaran-25** is the official Android application for the **Inter-IIIT Sports Fest** hosted by IIITs across India. The app brings live fixtures, real-time match updates, and leaderboards to users’ fingertips, creating a central hub for all sports-related information during the event.

---

## 🚀 Features

### 📅 Live Fixtures
- View ongoing and upcoming matches across all sports.
- Each fixture displays:
  - Match name
  - Time and date
  - Venue
  - Live scores and post-match results

### 🛠️ Admin Panel
- Admins can log in with credentials to:
  - Add, update, or delete fixtures
  - Update live scores in real-time
  - Modify team rankings and leaderboard entries

### 👥 User Access
- Regular users are automatically signed in as **guests**.
- Admins are required to log in with a username and password to access management tools.

### 🏅 Live Leaderboard
- Displays real-time rankings of participating IIITs based on event results.
- Updated dynamically as scores and outcomes are added.

### 📬 Contact Page
- Users can contact the organizers directly through the app.
- Submit queries or issues, which are routed to the admin panel for response.

---

## 🧱 Tech Stack

- **Frontend:** XML
- **Backend:** Firebase Firestore & Firebase Auth
- **Authentication:** Email/Password login for Admins; anonymous auth for Guests
- **Real-Time Database:** Used for live updates to fixtures and leaderboard

---

## 📦 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/twaran-25.git
   cd twaran-25
