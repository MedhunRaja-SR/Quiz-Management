import { ChecklistService } from './../Services/checklist.service';
import { Router } from '@angular/router';
import { SecurityService } from './../Services/security.service';
import { Component, OnInit } from '@angular/core';
import { SeverityService } from '../Services/severity.service';
import { QuizResponse } from '../Models/QuizResponse';

@Component({
  selector: 'app-severity',
  templateUrl: './severity.component.html',
  styleUrls: ['./severity.component.css']
})
export class SeverityComponent implements OnInit {
  status:string="";
  courseType:string="";
  quizResponse:QuizResponse = {
    userName: "",
    courseType:"",
    status: "",
    suggestion: "",
    score: 0,
  };
  
  constructor(
    private service:SeverityService,
    private router : Router,
    private securityService : SecurityService,
    private checklistService : ChecklistService,
    ) {  }
  
    
    getExecutionStatus() : void {
      let fetch : QuizResponse; 
    this.service.executionStatus()
    .subscribe(
        data => {
            fetch = data;
            //console.log(data);
            if(data.score==-1){  //valid check
              this.router.navigate(['backToLogin']);
            }
        }, 
        (err)=>{}, 
        ()=>{
          this.quizResponse = fetch;
          this.status = this.quizResponse.status;
        }
        );
      }

  ngOnInit(): void { 
    this.status = "";
    this.securityService.healthCheck().subscribe(
      (data)=>{
      },
      (err)=>{
        this.router.navigate(['error']);
      },
      ()=>{
        this.service.benchHealthCheck().subscribe(
          (data)=>{
          },
          (err)=>{
            this.router.navigate(['error']);
          },
          ()=>{
            this.service.quizHealthCheck().subscribe(
              (data)=>{
              },
              (err)=>{
                this.router.navigate(['error']);
              },
              ()=>{
                  this.securityService.checkAuthFromLocal("severity", "backToLogin");
                  if(localStorage.getItem("quizToken")!=null){
                    if(this.securityService.getLoginStatus() && !this.securityService.getSpecialFlag()){
                      this.getExecutionStatus();
                    }
                    else if(this.securityService.getLoginStatus() || !this.securityService.getSpecialFlag()){
                      this.securityService.turnOnSpecialFlag();
                      this.router.navigate(["checklist"]);
                    }
                    else{
                      this.router.navigate(['backToLogin']);
                    }
                  }
                  else{
                    this.router.navigate(['backToLogin']);
                  }
              });
          });
      });
    }
  }

  