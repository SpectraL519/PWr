import random
import datetime
from calendar import monthrange



def getPesel(date: datetime.datetime, sex: str) -> str:
    year = int(date.year)
    month = int(date.month)
    if year < 1900: month += 80
    elif year > 1999: month += 20
    else: pass
    day = int(date.day)

    pesel = ""
    pesel += f"{year % 100:02}"
    pesel += f"{month:02}"
    pesel += f"{day:02}"

    for i in range(3): pesel += str(random.randint(0, 9))
    if sex == 'M': pesel += str(random.choice([1, 3, 5, 7, 9]))
    elif sex == 'K': pesel += str(random.choice([0, 2, 4, 6, 8]))
    else: pass
    
    confirmation_number = 0
    weights = [1, 3, 7, 9]
    for i in range(len(pesel)): confirmation_number += int(pesel[i]) * weights[i % 4]
    confirmation_number %= 10
    if confirmation_number != 0: confirmation_number = 10 - confirmation_number
    pesel += str(confirmation_number)

    return pesel


def getRandomDate(age: int) -> datetime.datetime:
    time_now = datetime.datetime.now()
    curr_year = int(time_now.year)
    curr_month = int(time_now.month)
    curr_day = int(time_now.day)

    year = curr_year - age
    month = random.randint(1, curr_month) if year == curr_year else random.randint(1, 12)
    day = random.randint(1, curr_day) if month == curr_month and year == curr_year else random.randint(1, monthrange(year, month)[1])

    return (datetime.datetime(year, month, day)).date()



with open("people.txt", 'w') as f:
    for i in range(5):
        name = 'child_name_' + str(i)
        surname = 'child_surname_' + str(i)
        date = getRandomDate(age=random.randint(0, 17))
        sex = random.choice(['M', 'K'])
        pesel = getPesel(date, sex=sex)
        f.write(f"INSERT INTO Ludzie(PESEL, imie, nazwisko, data_urodzenia, plec) VALUES('{pesel}', '{name}', '{surname}', '{date}', '{sex}');\n")
    
    for i in range(45):
        name = 'adult_name_' + str(i)
        surname = 'adult_surname_' + str(i)
        date = getRandomDate(age=random.randint(19, 59))
        sex = random.choice(['M', 'K'])
        pesel = getPesel(date, sex=sex)
        f.write(f"INSERT INTO Ludzie(PESEL, imie, nazwisko, data_urodzenia, plec) VALUES('{pesel}', '{name}', '{surname}', '{date}', '{sex}');\n")

    for i in range(5):
        name = 'elderly_name_' + str(i)
        surname = 'elderly_surname_' + str(i)
        date = getRandomDate(age=random.randint(61, 110))
        sex = random.choice(['M', 'K'])
        pesel = getPesel(date, sex=sex)
        f.write(f"INSERT INTO Ludzie(PESEL, imie, nazwisko, data_urodzenia, plec) VALUES('{pesel}', '{name}', '{surname}', '{date}', '{sex}');\n")
