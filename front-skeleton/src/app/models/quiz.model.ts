export interface Quiz {
  id: number;
  title: string;
  category: string;
  description: string;
  level: 'Facile' | 'Moyen' | 'Difficile';
  players: number;
  duration: number; // temps total ou par question selon ton design
  questions: any[]; // optionnel si tu ne charges pas les questions à chaque fois
  createdById: number;
  createdByName: string;
  createdByEmail: string;
  questionIds?: number[];

  // 🔹 Autres champs éventuels
  rating?: number;
}
