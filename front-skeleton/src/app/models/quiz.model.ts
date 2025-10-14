
export interface Quiz {
  id: number;
  title: string;
  category: string;
  description: string;
  level: 'Facile' | 'Moyen' | 'Difficile';
  duration: number;
  players: number;
  rating: number;
  questions: any[];
  timeLimitPerQuestionSeconds?: number; // temps par question
  createdBy?: string;
}
