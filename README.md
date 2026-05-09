# 🏨 Hotel Abbot

A console-based Hotel Management System built in Java — featuring role-based access, real-time MySQL sync, and classic data structures put to practical use.

---

## 🚀 What It Does

| Role | Can Do |
|---|---|
| 👨‍💼 Administrator | Book rooms, check out customers, manage waiting list |
| 📊 Manager | View revenue, expenses, and monthly profit/loss |
| 👥 HR | Add, edit, remove employees, view salary totals & action log |

---

## ⚙️ Tech

- **Java** — OOP, data structures, JDBC
- **MySQL 8.0** — persistent storage for customers, employees, rooms & plans
- **IntelliJ IDEA** — recommended IDE

---

## 📁 Structure

```
HotelAbbot/
├── HotelManagementSystem.java  ← main entry point
├── Admin.java                  ← customer check-in/out + waiting list
├── HR.java                     ← employee management + action log
├── Manager.java                ← revenue & expense reporting
├── RPDAO.java                  ← DAO for rooms & plans(data acces object)
├── Customer / Employee / Plan / Room .java  ← models
├── DatabaseConnection.java     ← MySQL connection
└── hotel_abbot.sql             ← full DB setup script
```

---

## ▶️ Running the Project

**1. Set up the database**
Open MySQL Workbench → paste `hotel_abbot.sql` → run with `Ctrl + Shift + Enter`

**2. Add MySQL Connector/J**
IntelliJ → File → Project Structure → Libraries → add the JAR

**3. Check your credentials in `DatabaseConnection.java`**
```java
DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_abbot", "root", "YOUR_PASSWORD");
```

**4. Run `HotelManagementSystem.java`**

---

## 🔐 Login Credentials

| Role | Name | ID | Secret |
|---|---|---|---|
| Administrator | Yahya | 123 | — |
| Manager | Yahya | 321 | apple |
| HR | ABD | 456 | — |

---

## 🧠 Data Structures

| Structure | Used For |
|---|---|
| `HashMap` | O(1) customer & employee lookup by ID |
| `HashSet` | Prevent duplicate customer IDs |
| `Queue` | Waiting list — first come, first served |
| `TreeMap` | Employee salary ranking — auto sorted |
| `Stack` | HR action log — last action on top |
| `LinkedHashMap` | Revenue history — insertion order kept |

---

## 🗄️ Database

4 tables: `customers` · `employees` · `plans` · `rooms`

7 plans from **Basic ($60/night)** to **King Suite ($250/night)** · 13 rooms total
