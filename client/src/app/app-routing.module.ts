import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './chat/chat.component';
import { LoginComponent } from './authentication/login/login.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'chat' },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'chat',
    component: ChatComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
