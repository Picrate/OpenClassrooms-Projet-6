import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Topic} from "../../interfaces/topic";
import {PostsApiService} from "../../services/posts-api.service";
import {NewPostRequest} from "../../interfaces/new-post-request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent implements OnInit {

  topics!: Topic[];
  topic?: Topic;
  messageSeverity: string = 'success';
  responseMessage: string ='';
  onMessage: boolean = false;

  postForm = new FormGroup({
    topic : new FormControl('', Validators.required),
    title : new FormControl('', Validators.required),
    content : new FormControl('', Validators.required)
  });

  get f(){
    return this.postForm.controls;
  }

  constructor(private postApiService: PostsApiService, private router: Router) {}

  ngOnInit(): void {}

  // TODO Traiter le retour

  onSubmit() {
    const newPost: NewPostRequest = {
      title: this.postForm.value.title,
      content: this.postForm.value.content,
      topic: this.topics[0]
    }
    console.log(newPost);
    this.postApiService.createNewPost(newPost).subscribe(response => {
      this.onMessage = true;
      this.messageSeverity = 'success';
      this.responseMessage = 'Article Enregistré';
      this.router.navigate(['/posts/'+response.message]);

    }, error => {
      this.onMessage = true;
      this.messageSeverity = 'error'
      this.responseMessage = error.errorMessage;
    });
  }

  search($event: any) {
   this.postApiService.getTopicsByTitle($event.query).subscribe( {
     next: value => {
       this.topics = value;
     }
   });
  }
}
