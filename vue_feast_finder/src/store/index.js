import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      currentBackground: '../../dinerBackground.jpg',
      currentSearch: '',
      showRestaurants: false,
      showGroupForm: false,
      category: '',
      term: '',
      longitude: '',
      latitude: '',
      isLoading: false,
      voter_id: -1,
      moreDetailsView: false,
      //from here down will need to be removed when end points are created
      groups: [],
      showVoteView: false,
      areGroupButtonsVisible: [],
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      UPDATE_BACKGROUND(state, string) {
        state.currentBackground = string;
      },
      SET_SEARCH_TERM(state, search) {
        state.currentSearch = search;
      },
      SET_NARROW_TERM(state, term) {
        state.term = term;
      },
      TOGGLE_RESTAURANTS(state, show) {
        state.showRestaurants = show;
      },
      TOGGLE_GROUP_FORM(state, show) {
        state.showGroupForm = show;
      },
      SET_CATEGORY(state, category) {
        state.category = category;
      },
      SET_LOCATION(state, location) {
        state.longitude = location.longitude;
        state.latitude = location.latitude;
      },
      TOGGLE_VOTE_VIEW(state, view) {
        state.showVoteView = view;
      },
      SET_LOADING(state, loading) {
        state.isLoading = loading;
      },
      SET_VOTER_ID(state,id){
        state.voter_id = id;
      },
      TOGGLE_DETAILS_VIEW(state) {
        state.moreDetailsView = !state.moreDetailsView;
      },
    },
  });
  return store;
}
