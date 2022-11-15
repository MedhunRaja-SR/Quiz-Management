import { Microservices } from './../Models/Microservices';
import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable} from 'rxjs';
import { Question } from '../CheckList/question';


@Injectable({
  providedIn: 'root'
})
export class ChecklistService {

  readonly APIUrl=Microservices["checklist"];
  private _responses: Question[] = [];

  public get responses(): Question[] {
    return this._responses;
  }

  getQuestionsFromMS(type: string) : Observable<Question[]> {
    return this.http.post<Question[]>(this.APIUrl+'/QuizCheckListQuestions',{'courseType':type } );
    
  }

  healthCheck() : Observable<any> {
    return this.http.get<any>(this.APIUrl+'/health-check',{responseType:'text' as 'json'});
  }

  getResponse(responses: Question[]) : void {
    this._responses = responses;
    //this.sendResponse();
  }
  
  sendResponse() : Question[] {
    return this._responses;
  }
  
  validated(questions: Question[]) {
    this._responses=questions;
    return true;
  } 
  
  getCourseType() : string{
    return this._responses[0].courseType;
  }

  constructor(private http:HttpClient) { }

}
