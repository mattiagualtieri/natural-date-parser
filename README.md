# natural-date-parser

✅ Basic Relative Dates
```
<special>
today
tomorrow
yesterday
the day after tomorrow
the day before yesterday
```

✅ Relative Offsets (Units)
```
<in> <number> <unit>
<number> <unit> <ago>
in 5 minutes
in 30 mins
in 2 hours
in three hours
in one hour
in 10 days
in 2 weeks
in 3 months
in 1 year
3 days ago
two weeks ago
one month ago
```

✅ Weekday-based Expressions
```
<weekday>
<next>/<last>/<this> <weekday>
monday
next monday
last friday
this tuesday
next wed
thu
this weekend
next weekend
```

✅ Date + Time
```
<weekday> <at> <time>
<weekday> <at> <special>
<next>/<this> <weekday> <at> <time>
<special> <at> <time>
<time> <of> <special> -> 12:00 of yesterday
friday at 5pm
next monday at 14:30
tomorrow at noon
today at midnight
yesterday at 23:59
```

✅ Absolute Dates (Month + Day)
```
<month> <number>
<number> <month>
<month> <ordinal>
<ordinal> <of> <month> [<at> <time>]
january 5
jan 5
5 january
5 jan
march 21st
21st of march
august 3rd at 10am
15 dec at 17:00
```

✅ Absolute Dates (Month + Day + Year)
```
june 5 2024
jun 5, 2024
5 june 2024
5 jun 2024
december 1st, 2023 at 3pm
```

✅ Absolute Dates Using Numbers Only
```
<date> <at> <time>
<time> <date>
<date> <time>
10/02/2025
02/10/2025
2025-02-10
2025/02/10 at 7pm
```

✅ Complex Relative Phrases
```
two hours from now
three days from today
a week from friday
the day after next monday
the monday after next
```

✅ Mixed Number Words & Digits
```
twenty-one days from now
one hundred and twenty minutes ago
five hundred seconds ago
thirty-five days from today
```

✅ Fuzzy Inputs
```
tmrw at 5
tmr at 5pm
mon at 9
fri noon
sat evening
sun morning
```

✅ Ambiguous Inputs
```
next monday at 10
this friday or next?
10/11/12
five to six
half past ten
quarter past 5
```

✅ Time-Only Inputs
```
5pm
17:00
5:30
8 in the morning
2 at night
```

✅ Human Phrases
```
around 5pm
just after noon
just before 8
about an hour ago
```
