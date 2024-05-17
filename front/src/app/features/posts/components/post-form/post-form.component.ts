import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Topic} from "../../interfaces/topic";
import {PostsApiService} from "../../services/posts-api.service";
import {map, Observable, startWith} from "rxjs";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent implements OnInit {

  topics!: Topic[];
  filteredTopics: Observable<Topic[]> | undefined;

  topicForm = new FormGroup({
    topic : new FormControl<string | Topic>(''),
    title : new FormControl(''),
    content : new FormControl('')
  });

  constructor(private postApiService: PostsApiService) {}

  ngOnInit(): void {
    this.postApiService.getTopics().subscribe(topics => this.topics = topics);
    this.filteredTopics = this.topicForm.get('topic')?.valueChanges.pipe(
      startWith(''),
      map(value => {
        const title = typeof value === 'string' ? value : value?.title;
        return title ? this._filter(title as string):this.topics.slice();
      })
    )
  }

  private _filter(name: string): Topic[] {
    const filterValue = name.toLowerCase();
    return this.topics.filter(topic => topic.title.toLowerCase().includes(filterValue));
  }

  onSubmit() {
  console.log(this.topics);
  }
}
