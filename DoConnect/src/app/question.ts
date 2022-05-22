export class Question {
    toLocaleLowerCase(): { [Symbol.match](string: string): RegExpMatchArray | null; } {
        throw new Error('Method not implemented.');
    }
    id: number = 0;
    question: string = "";
    status: string = "";
}
