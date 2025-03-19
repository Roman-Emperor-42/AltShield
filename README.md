# AltShield - Minecraft Alt Account Protection

![AltShield](https://img.shields.io/badge/Minecraft-1.20+-blue.svg) ![Version](https://img.shields.io/badge/Version-1.21-green.svg) ![License](https://img.shields.io/badge/License-MIT-orange.svg)

**AltShield** is a lightweight yet powerful **alt account detection** plugin designed for Minecraft servers. It prevents players from using multiple accounts on the same IP while providing **configurable limits and database storage** to ensure privacy and security.

## 🚀 Features
- ✅ **Detect & Block Alts** - Restricts players from using multiple accounts on the same IP.
- ✅ **Database Storage** - Uses **SQLite** (or MySQL in future versions) for secure data handling.
- ✅ **Highly Configurable** - Customize max accounts, max IPs, bypass permissions, and messages.
- ✅ **Bypass System** - Admins and trusted players can bypass restrictions.
- ✅ **Easy to Use** - Simple commands for reloading and checking alt accounts.

## 📥 Installation
1. Download the latest version of **AltShield.jar** from the [Releases](https://github.com/AkaTriggered/AltShield/releases) page.
2. Place it in your server's `plugins/` folder.
3. Restart or reload your server.
4. Configure `config.yml` to match your server needs.

## ⚙️ Configuration (`config.yml`)
```yaml
# AltShield Configuration
max_accounts: 2 # Maximum accounts per IP
max_ips: 3 # Maximum IPs per player
bypass_permission: "altshield.bypass"
kick_message: "&cYou have reached the maximum accounts allowed on this IP!"
messages:
  reload: "&aAltShield configuration reloaded successfully!"\  bypass: "&e[AltShield] Allowing {player} (Has bypass permission)"
```

## 💾 Database
- **Local Storage:** Uses `altshield.db` (SQLite) inside the plugin folder.
- **Future Support:** MySQL integration is planned.
- **Check Data:** Use `DB Browser for SQLite` or run `sqlite3 plugins/AltShield/altshield.db "SELECT * FROM player_data;"`

## 🔍 Commands & Permissions
| Command | Description | Permission |
|---------|-------------|------------|
| `/altshield reload` | Reloads the plugin configuration | `altshield.admin` |
| `/checkalt <player>` | Checks alt accounts linked to a player | `altshield.admin` |

## 🛠️ Testing Alt Detection
### ✅ Test Multiple Accounts on Same IP:
1. Login with **Player1**.
2. Login with **Player2** from the **same IP**.
3. If `max_accounts` is exceeded, Player2 should be **kicked**.

### ✅ Test Different IPs on Same Account:
1. Use a **VPN** or mobile hotspot.
2. Login with **Player1** from multiple IPs.
3. If `max_ips` is exceeded, Player1 should be **kicked**.

### ✅ Test Bypass Permission:
1. Give a player `altshield.bypass`.
2. Login with multiple accounts.
3. They should **not** be kicked.

## 📌 Future Plans
- ✅ MySQL Database Support
- ✅ Web Dashboard for Monitoring
- ✅ IP Whitelist System

## 📸 Screenshots
![Screenshot 1](https://media.discordapp.net/attachments/1350825813578088459/1351932487344984064/image.png?format=webp&quality=lossless)
![Screenshot 2](https://media.discordapp.net/attachments/1350825813578088459/1351932209354899497/image.png?format=webp&quality=lossless)
![Screenshot 3](https://media.discordapp.net/attachments/1350825813578088459/1351932102861652031/image.png?format=webp&quality=lossless)
![Screenshot 4](https://media.discordapp.net/attachments/1351557728023347303/1351927955961413713/image.png?format=webp&quality=lossless)

## 🔗 Links
- [GitHub Repository](https://github.com/AkaTriggered/AltShield)
- [Releases](https://github.com/AkaTriggered/AltShield/releases)
- [Issue Tracker](https://github.com/AkaTriggered/AltShield/issues)

## 📜 License
This project is licensed under the **MIT License**. Feel free to modify and contribute!

---

🎉 **Thank you for using AltShield!** If you have suggestions or find bugs, create an [issue](https://github.com/AkaTriggered/AltShield/issues)! 🚀

