import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Topic} from "../../interfaces/topic";
import {PostsApiService} from "../../services/posts-api.service";
import {map, Observable, startWith} from "rxjs";
import {NewPostRequest} from "../../interfaces/new-post-request";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent implements OnInit {

  topics!: Topic[];
  topic?: Topic;

  topicForm = new FormGroup({
    title : new FormControl('', Validators.required),
    content : new FormControl('', Validators.required)
  });

  constructor(private postApiService: PostsApiService) {}

  ngOnInit(): void {}

  // TODO Traiter le retour
  onSubmit() {
    const newPost: NewPostRequest = {
      title: this.topicForm.value.title,
      content: this.topicForm.value.content,
      topic: this.topic
    }
    this.postApiService.createNewPost(newPost).subscribe(response => {
      console.log(response);
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
