import { File } from '../../shared/domain/file.model'

export class WebSiteConfig{

    private name:string;

    backgroundImageFile: File;
    logo500ImageFile:string;
    logo300ImageFile:string;
    
    formContactKey:string;
    email:string;

    fbUrl:string;
    twitterUrl:string;
    instaUrl:string;


    constructor(){
    }
}