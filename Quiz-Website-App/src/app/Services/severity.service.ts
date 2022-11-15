import { Microservices } from './../Models/Microservices';
import { SecurityService } from './security.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ChecklistService } from './checklist.service';
import { Question } from '../CheckList/question';
import { QuizResponse } from '../Models/QuizResponse';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
@Injectable({
  providedIn: 'root'
})
export class SeverityService {
  constructor(
    private http:HttpClient,
    private checklistService:ChecklistService,
    private securityService : SecurityService,
    private datePipe: DatePipe)
   {}
  setQuizResponse(data: QuizResponse) {
    throw new Error('Method not implemented.');
  }

  public quizHealthCheck(){
    return this.http.get(Microservices["severity"]+"/health-check",{ responseType : 'text'});
  }

  public benchHealthCheck(){
    return this.http.get(Microservices["benchmark"]+"/health-check",{ responseType : 'text'});
  }

  public getResponses() : Question[]{
    return this.checklistService.sendResponse();
  }

  public executionStatus() : Observable<QuizResponse> {
    return this.http.post<QuizResponse>(Microservices["severity"]+"/QuizExecutionStatus",
      {
        "userName": this.securityService.getProjectDetails().Name,
        "quizDetail":{
          "courseType":this.checklistService.getCourseType(),
          "quizDate":this.datePipe.transform(new Date(),"yyyy-MM-dd"),
          "quizQuestions":this.getResponses()
        }
      }
    );
  }

}
