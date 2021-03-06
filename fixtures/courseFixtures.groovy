import braid.Link
import braid.UserCourse
import braid.course.Course
import braid.course.Option
import braid.course.Settings

load "userFixtures"

fixture{

	tallerSettings(Settings,
	options:
		Option.homework.value +
		Option.assignment.value +
		Option.codeReview.value,
	bannerColor: '#248348',
	githubUsername: 'tallerweb')

	taller(Course,
	name:'Taller Web 1 - Java',
	university:'Universidad Nacional de La Matanza',
	settings: tallerSettings,
	syllabus:'<h2>Syllabus placeholder</h2><p>Some text</p>',
	honorCode:'<h2>Honor code placeholder</h2><p>Some text</p>',
	active: true)

	uc1(UserCourse, user: obi, course: taller, approved: true)
	uc2(UserCourse, user: anakin, course: taller, approved: true)
	uc3(UserCourse, user: leia, course: taller, approved: true)
	//	uc3(UserCourse, user: jarjar, course: taller)

	link1(Link,
	caption: 'Grupo',
	url: 'https://groups.google.com/forum/?fromgroups#!forum/tallerwebjava',
	position: 1,
	course: taller)
	link2(Link,
	caption: 'Repo GitHub',
	url: 'https://github.com/tallerweb',
	position: 2,
	course: taller)
	link3(Link,
	caption: '"Notas" del Taller',
	url: 'https://github.com/tallerweb/notas',
	position: 3,
	course: taller)
	link4(Link,
	caption: 'Wiki',
	url: 'https://github.com/tallerweb/notas/wiki',
	position: 4,
	course: taller)
	link5(Link,
	caption: 'Feedback',
	url: 'https://docs.google.com/spreadsheet/viewform?formkey=dDdYNnIzRWFiV2FwbGtyNjA3a2NFZGc6MQ#gid=0',
	position: 5,
	course: taller)


	aypSettings(Settings,
	options:
		Option.homework.value +
		Option.codeReview.value,
	bannerColor: '#006',
	githubUsername: 'ayp')

	ayp(Course,
	name:'Algoritmos y Programación 1',
	university:'Universidad Nacional de Tres de Febrero',
	settings: aypSettings,
	syllabus:'<h2>Syllabus placeholder</h2><p>Some text</p>',
	honorCode:'<h2>Honor code placeholder</h2><p>Some text</p>',
	active: true)

	uc4(UserCourse, user: quigon, course: ayp, approved: true)
	uc5(UserCourse, user: luke, course: ayp, approved: true)
	//	uc6(UserCourse, user: jarjar, course: ayp)

	link11(Link,
	caption: 'Grupo',
	url: 'https://groups.google.com/forum/?fromgroups#!forum/lp1-untref',
	position: 1,
	course: ayp)
	link12(Link,
	caption: 'Repo GitHub',
	url: 'https://github.com/ayp',
	position: 2,
	course: ayp)

	uc10(UserCourse, user: obi, course: ayp, approved: true)

	corteSettings(Settings,
	options: 0,
	bannerColor: '#006',
	githubUsername: 'ayp')

	corte(Course,
	name:'Corte y confección 4',
	university:'Universidad Nacional de La Matanza',
	settings: corteSettings,
	syllabus:'<h2>Syllabus placeholder</h2><p>Some text</p>',
	honorCode:'<h2>Honor code placeholder</h2><p>Some text</p>',
	active: false)

	uc20(UserCourse, user: luke, course: corte, approved: true)
	uc21(UserCourse, user: phantom, course: corte, approved: true)


}
