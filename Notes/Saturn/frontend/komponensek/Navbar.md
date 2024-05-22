## Menüpontok
---
- Logo 
- [[Főoldal]]/[[Főoldal|Dashboard]] 
- Tárgyaim kezelése _oktató only_
- Tárgyfelvétel _hallgató only_
- Vizsgafelvétel _hallgató only_ (ld.: [[időszakok]])
- Órarend _oktató + hallgató only_ 
- Ügyintézés : mindenkinek, ügyintézőknek külön funkcionalitás
	- Kérvények
	- Tartozások
- Jobb sarokban Felhasználói preferenciák ikon (Profil kör)

# v2
Tanuló
- Szemeszterek
	- current diák -> bejelentkezés félévre (create SemesterRegistration)
- Tárgyfelvétel 
	- if !currentDiák.hasCurrentSemester -> must register to semester
	- else get all subjects where registerableperiodofYear is selectedSemester.periodOfYear
		- Subject -> név, tárgyfelelősök, kreditindex
		- where subject_id in current_user.subjectregistrations -> bg_color changed / checkbox ticked
- Tárgyfelvétel - clicked on subject in row
	- get Subject.courses (get all courses where subject.id = clicked_id) where courseType != exam
	- courses shown in popup
		- Course -> név, típus, dayofWeek, location, startTime + endtime (calc from length)
		- where course_id in current_user.courseRegistrations -> bg_color changed / checkbox ticked

- Órarend 
	- Get currentUser.semesterRegistration where semesterRegistration.semester = selectedSemester
	- get subjectRegistrations.courseRegistrations from semesterRegistration
	- list of courseRegistrations -> name, type, location, dayofWeek, start + endTime -> construct calendar
IMPROVEMENT: PÁROS PÁRATLAN HÉT, ACTUAL CALENDAR (most csak list a cél)
- Tanulmányi átlagok
	- get all semesterRegistrations of currentUser
	- calc avg of subjectregistrations. grade per semesterRegistration
- Vizsgajelentkezés
	- get currentUser.semesterRegistration where semesterRegistration.semester == currentSemester
	- get semesterRegistration.subjectRegistration.subject.courses where courseType == exam
- Kérvények
	- STUDENT:
		- Title of requests initiated by me
		- List of requests where requesterUuid == currentUser.uuid
		- See state
	- MANAGER:
		- Title of requests handled by me
		- List of requests where assignee == currentUser
- Pénzügy
	- STUDENT:
		- Title of pénzügyi tartozásaim
		- List of monetary cuccaim not yet handled
	- MANAGER:
		- List of pénzügyi tartozások where != handled
		- Can create new pénzügyi tartozás -> for all Users where studentUuid != null -> get All students, get all users by studentUuid
Tanár
- Tárgyak kezelése
	- MANAGER:
		- create subject, add subjectDeputy, etc
		- subjectDeputyba mehet minden teacheruuid useruuid
	- TEACHER:
		- list all subjects where teacheruuid in subjectdeputies
		- edit subject, add subjectDeputy, etc
		- edit courseTeachers, add/edit course
- Érdemjegy beírása
	- coursehoz courseDeputy - kell eredményértékelés entity bound to course, studentuuid?
	- subjecthez subjectDeputy -> grade
Menedzser
- ~~Kérvényeim kezelése~~
- Felhasználó meghívása -> fill user info, create user entity based on that, student/teacher/manager based on roles -> somehow create user in keycloak?
- ~~Pénzügyi tartozások~~
