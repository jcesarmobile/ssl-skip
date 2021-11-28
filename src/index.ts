import { registerPlugin } from '@capacitor/core';

import type { SslSkipPlugin } from './definitions';

const SslSkip = registerPlugin<SslSkipPlugin>('SslSkip', {
  web: () => import('./web').then(m => new m.SslSkipWeb()),
});

export * from './definitions';
export { SslSkip };
