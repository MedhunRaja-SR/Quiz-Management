export class ProjectDetails{
    private name : string = "";
    private valid : boolean = false;
    constructor(
        
    ){}

    public get Name() : string{
        return this.name;
    }

    public get Valid() : boolean{
        return this.valid;
    }
    public set Name(name){
        this.name = name;
    }

    public set Valid(valid){
        this.valid = valid;
    }
}