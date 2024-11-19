import axios from 'axios';

export default {
    getVotes() {
        return axios.get(`/votes`);
    },
    getVote(id) {
        return axios.get(`/vote/${id}`)
    },
    createGroup(poll) {
        return axios.post(`/vote`, poll);
    },
    //FOCUS HERE
    addEatery(voteId, eatery) {
        console.log(voteId, eatery);
        return axios.post(`/eatery/${voteId}`, eatery);
    },
    getEateries(voteId) {
        return axios.get(`/eateries/${voteId}`);
    },
    getEatery(voteId) {
        return axios.get(`/eatery/${voteId}`);
    },
    setVoterName(voter_name, vote_id) {
        return axios.post(`/voter/${voter_name}/${vote_id}`);
    },
    //needs to be vote_id
    castVote(vote, eatery_id, voter_id) {
        let data = { eatery_response: vote, eatery_id: eatery_id, voter_id: voter_id };
        return axios.post(`/addEateryVote`, data);
    },
    getVoteTrueCount(eateryId) {
        return axios.get(`/eateryVotes/${eateryId}/true`);
    },
    getVoteFalseCount(eateryId) {
        return axios.get(`/eateryVotes/${eateryId}/false`);
    },

}