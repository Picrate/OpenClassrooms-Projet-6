import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {Observable} from "rxjs";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  @Input() postList!: Observable<Post[]>;

  constructor() { }

  ngOnInit(): void {

  }

}
