import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {};

const postListSilce = createSlice({
    name: "postList",
    initialState: {value: initialStateValue},
    reducers: {
        readPosts: (state, action) => {
            state.value = action.payload
        }
    }
})

export const readPosts = postListSilce.actions;

export default postListSilce.reducer;