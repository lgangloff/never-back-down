export class I18nValue{

    id: number;
    langKey:string;
    value:string;

    constructor(langKey:string){
        this.langKey = langKey;
    }
}