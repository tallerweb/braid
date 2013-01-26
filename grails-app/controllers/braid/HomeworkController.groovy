package braid


class HomeworkController {

	def userService
	def courseService

    def list() {
		
		def currentCourse = courseService.currentCourse
		def homeworks = Homework.findAllByCourse(currentCourse,[sort: 'dueDate'])
		
		model: [homeworks: homeworks]
	}
	
	def show(Long id) {
		
		def user = userService.currentUser
		def homework = Homework.get(id)
		
		// TODO: cuando se tenga la entidad UserCourse con 
		// role podemos pedir user.getRoleInCourse(course)
		// y mejorar esto por una llamada dinámica.
		
		if (user.hasRole('JEDI')) {
			showIfJedi(homework)
		} 
		if (user.hasRole('PADAWAN')) {
			showIfPadawan(homework)
		}
		
	}
	
	private showIfPadawan(Homework homework) {
		
		def user = userService.currentUser
		def currentCourse = courseService.currentCourse
		
		def previousSolution = HomeworkSolution.findByUserAndHomework(user, homework)
		def alreadySolved = previousSolution != null
		def command = previousSolution
		
		render view: 'showIfPadawan', model: [homework: homework, course: currentCourse,
			command: command, alreadySolved: alreadySolved]
	}
	
	private showIfJedi(Homework homework) {
		
		def totalRespuestas = HomeworkSolution.countByHomework(homework)
		
		def c = HomeworkSolution.createCriteria()
		def totalACorregir = c.count() {
			eq('homework', homework)
			isNull('feedback')
		}
		
		boolean esMomentoDeCorregir = homework.dueDate < new Date()
		boolean hayQueCorregir = totalACorregir > 0
		
		render view: 'showIfJedi', model: [homework: homework,
			totalRespuestas: totalRespuestas, hayQueCorregir: hayQueCorregir,
			esMomentoDeCorregir: esMomentoDeCorregir]
	}

	def solve(Long homeworkId, HomeworkSolutionCommand command) {
		
		def homework = Homework.get(homeworkId)
		def course = courseService.currentCourse
		
		if (!homework.outOfDate) {
			
			if (command.validate()) {
				def user = userService.currentUser
				
				def solution = command.toHomeworkSolution()
				solution.homework = homework
				solution.user = user
				
				solution.save(flush: true)
				
				flash.message = 'Tu respuesta se ha guardado correctamente. Recordá que podés corregirla dentro del período de vigencia'
				redirect action: 'show', params: [id: homeworkId]
				
			} else {
				render view:'showIfPadawan', model: [homework: homework, course: course, command: command]
			}
		} else {
			flash.message = 'No se puede responder fuera de fecha'
			redirect action: 'show', params: [id: homeworkId]
		}
	}
	
	def grade(Long homeworkId) {
		def homework = Homework.get(homeworkId)

		def c = HomeworkSolution.createCriteria()
		
		// TODO: cuando tenga internet refactorizar, se puede pedir uno sólo
		def homeworkSolution = c.list() {
			eq('homework', homework)
			isNull('feedback')
		}[0]
		
		if (! homeworkSolution) {
			redirect action: 'show', params: [id: homeworkId]
		}
		
		[homework: homework, homeworkSolution: homeworkSolution]
		
	}
	
	def gradeDo(Long homeworkSolutionId, String feedback) {
		def homeworkSolution = HomeworkSolution.get(homeworkSolutionId)
		homeworkSolution.feedback = feedback
		homeworkSolution.save(flush: true)
		
		redirect action:'grade', params: [homeworkId: homeworkSolution.homework.id]
	}
	
}
