import {Buffer} from "buffer";

function parseToken(token){

    const base64Payload = token.split('.')[1];
    const payload = Buffer.from(base64Payload, 'base64');
    const result = JSON.parse(payload.toString());

    const expiryTime = result.exp;
    const role = result.role;
    const id = result.sub;

    return [expiryTime, role, id];
}

export default parseToken;