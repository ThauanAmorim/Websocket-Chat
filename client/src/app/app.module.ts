import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';
import { ChatListComponent } from './components/chat-list/chat-list.component';
import { UserComponent } from './components/chat-list/user/user.component';
import { ChatPanelComponent } from './components/chat-panel/chat-panel.component';
import { MessageComponent } from './components/chat-panel/message/message.component';

import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './authentication/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    ChatPanelComponent,
    MessageComponent,
    LoginComponent,
    ChatListComponent,
    UserComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatPaginatorModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
